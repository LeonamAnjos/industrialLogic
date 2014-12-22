// ***************************************************************************
// Copyright (c) 2013, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.crrap;

import static java.math.BigDecimal.ROUND_HALF_UP;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import untouchable.RecordSet;
import untouchable.RiskAssessor;

public class AssetReport {

	private static final BigDecimal PERCENTAGE_BASE = new BigDecimal(100);
	private RiskAssessor assessor;
	private BigDecimal allPositions;
	private TreeMap<String, BigDecimal> groupTotal;
	private TreeMap<String, BigDecimal> positions;
	private HashMap<String, BigDecimal> riskTable;
	private HashMap<String, String> assetToGroup;

	public AssetReport(RiskAssessor assessor) {
		this.assessor = assessor;
	}

	public AssetReport() {
		this(RiskAssessor.getInstance());
	}

	public void execute(RecordSet records, PrintWriter writer) {
		initializeFields();
		processAssetReport(records);
		printAssetReport(writer);
	}

	protected void printAssetReport(PrintWriter writer) {
		writer.write("<groups>\n");
		
		Iterator<String> g = groupTotal.keySet().iterator();
		while (g.hasNext()) {
			String group = g.next();
			BigDecimal groupPosition = groupTotal.get(group);
			BigDecimal groupWeight = calculateWeight(groupPosition, allPositions);
			
			writer.write("\t<group position='" + groupPosition.toPlainString());
			writer.write("' weight='" + groupWeight);
			writer.write("'>\n");
			writer.write("\t\t" + group + "\n");
			Iterator<String> i = positions.keySet().iterator();
			boolean notFirstOne = false;
			while (i.hasNext()) {
				String asset = i.next();
				// Output asset only if it belongs in group
				if (assetToGroup.get(asset).equalsIgnoreCase(group)) {
					if (notFirstOne)
						writer.write("\n");
					writer.write("\t\t<asset position='"
							+ positions.get(asset).toPlainString() + "' ");
					
					BigDecimal position = positions.get(asset);
					BigDecimal weight = calculateWeight(position, groupPosition);
					
					writer.write("weight='" + weight + "' risk='"
							+ riskTable.get(asset).toPlainString() + "'>\n");
					writer.write("\t\t\t" + asset + "\n");
					writer.write("\t\t</asset>");
					notFirstOne = true;
				}
			}
			writer.write("\n\t</group>\n");
		}
		writer.write("</groups>\n");
		writer.flush();
	}

	protected BigDecimal calculateWeight(BigDecimal position, BigDecimal totalPositions) {
		BigDecimal product = position.multiply(PERCENTAGE_BASE);
		BigDecimal weight = product.divide(totalPositions, 2, ROUND_HALF_UP);
		return weight;
	}

	protected void processAssetReport(RecordSet records) {
		for (int row = 0; row < records.getRowCount(); row++) {
			String assetName = records.getItem(row, "ISSUE_NAME");
			String assetGroup = records.getItem(row, "ISSUE_GROUP");
			assetToGroup.put(assetName, assetGroup);

			BigDecimal position = calculatePosition(records, row);
			BigDecimal riskCoefficient = calculateRiskCoefficient(records, row);
			BigDecimal risk = calculateRisk(riskCoefficient, position);

			riskTable.put(assetName, risk);
			allPositions = allPositions.add(position);
			positions.put(assetName, position);

			updateGroupTotal(assetGroup, position);
		}
	}

	protected void updateGroupTotal(String assetGroup, BigDecimal position) {
		BigDecimal value = new BigDecimal("0");
		if (groupTotal.containsKey(assetGroup))
			value = groupTotal.get(assetGroup);
		value = value.add(position);
		groupTotal.put(assetGroup, asCurrency(value));
	}

	private BigDecimal asCurrency(BigDecimal value) {
		return value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	private void initializeFields() {
		groupTotal = new TreeMap<String, BigDecimal>();
		positions = new TreeMap<String, BigDecimal>();
		
		allPositions = new BigDecimal("0.00");

		riskTable = new HashMap<String, BigDecimal>();
		assetToGroup = new HashMap<String, String>();
	}

	protected BigDecimal calculateRiskCoefficient(RecordSet records, int row) {
		BigDecimal riskCoefficient;
		if (isFundType(records.getItem(row, "ISSUE_FAMILY"))) {
			riskCoefficient = assessor.getRiskCoefficient(records.getItem(row, "ISSUE_FAMILY"), records.getDecimal(row, "TERM_TWO"));
		} else {
			riskCoefficient = records.getDecimal(row, "TERM_TWO");
		}
		return riskCoefficient;
	}

	protected BigDecimal calculatePosition(RecordSet records, int row) {
		BigDecimal termOne = records.getDecimal(row, "TERM_ONE");
		BigDecimal marketPrice = records.getDecimal(row, "MARKET_PRICE");
		BigDecimal quantity = records.getDecimal(row, "QUANTITY");
		
		BigDecimal position;
		String assetType = records.getItem(row, "ISSUE_FAMILY");
		if (isFundType(assetType)) {
			// position = quantity * (market-unit price[TERM_TWO])
			BigDecimal perItem = marketPrice.subtract(termOne);
			position = perItem.multiply(quantity);
		} else {
			// position = (quantity * market) - total price[TERM_ONE]
			position = quantity.multiply(marketPrice);
			position = position.subtract(termOne);
		}
		
		return asCurrency(position);
	}

	protected boolean isFundType(String assetType) {
		return assetType.toUpperCase().startsWith("FUND");
	}

	protected BigDecimal calculateRisk(BigDecimal product, BigDecimal position) {
		product = product.multiply(position);
		return product.divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP);
	}

}

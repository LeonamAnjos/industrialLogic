// ***************************************************************************
// Copyright (c) 2014, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.collections;

public class Map {
	protected List keys = new List();
	protected List values = new List();
	private boolean readOnly;
	

	public boolean isEmpty() {
		return size() == 0;
	}

	public void add(Object key, Object value) {
		if (readOnly)
			return;
		
		int index = getKeyPosition(key);
		if (index >= 0) {
			values.set(index, value);
			return;
		}
		
		keys.add(key);
		values.add(value);
	}
	
	public int size() {
		return values.size();
	}

	public boolean remove(Object key) {
		if (readOnly)
			return false;
		
		int index = getKeyPosition(key);
		if (index >= 0) {
			keys.removeElementAt(index);
			values.removeElementAt(index);
			return true;
		}
		
		return false;
	}

	public boolean contains(Object value) {
		for (int i = 0; i < size(); i++)
			if (isValueEqualAt(value, i))
				return true;
		return false;
	}

	private boolean isValueEqualAt(Object value, int i) {
		return (values.get(i) == value) || (values.get(i).equals(value));
	}

	public boolean containsKey(Object key) {
		return getKeyPosition(key) >= 0;
	}

	private boolean isKeyEqualAt(Object key, int i) {
		return keys.get(i) != null && keys.get(i).equals(key);
	}

	public Object get(Object key) {
		int index = getKeyPosition(key);
		if (index >= 0)
			return values.get(index);
		return null;
	}

	public int capacity() {
		return keys.capacity();
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}

	public void addAll(Map m) {
		for (int i=0; i<m.size(); i++) 
			add(m.keys.get(i), m.values.get(i));
	}
	
	private int getKeyPosition(Object key) {
		for (int i = 0; i < size(); i++)
			if (isKeyEqualAt(key, i))
				return i;
		return -1;
	}
}

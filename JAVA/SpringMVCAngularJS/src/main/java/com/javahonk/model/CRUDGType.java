package com.javahonk.model;

/**
 * @author binhtt
 * This Enum is created for (Create, Read, Update, Delete and Grant) Database operations/Access Right
 */
public enum CRUDGType {
	CREATE("Create", "C", 0),
	READ("Read", "R", 1), 
	UPDATE("Update", "U", 2), 
	DELETE("Delete", "D", 3), 
	GRANT("Grant", "G", 4);
	
	private String type;
	private String typeShort;
	private int bitOrder;
	
	CRUDGType(String type, String typeShort, int bitOrder){
		this.type = type;
		this.typeShort = typeShort;
		this.bitOrder = bitOrder;
	}

	
	
	/**
	 * @param bitOrder
	 * @return
	 */
	public static CRUDGType getCRUDGTypeByBitOrder(int bitOrder){
		CRUDGType type = null;
		switch (bitOrder) {
		case 0:
			type = CREATE;
			break;
		case 2:
			type = CRUDGType.UPDATE;
			break;
		case 3:
			type = CRUDGType.DELETE;
			break;
		case 4:
			type = CRUDGType.GRANT;
			break;
		default:
			type = CRUDGType.READ;
			break;
		}
		
		return type;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	
	/**
	 * @return the typeShort
	 */
	public String getTypeShort() {
		return typeShort;
	}

	
	/**
	 * @return the bitOrder
	 */
	public int getBitOrder() {
		return bitOrder;
	}
	
}

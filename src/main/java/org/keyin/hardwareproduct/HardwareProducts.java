package org.keyin.hardwareproduct;

public class HardwareProducts {

    private int itemId;
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int qty_inStock;

    public HardwareProducts (){}


    // constructors below default
    // starting with name typew price and qty in stock

    public HardwareProducts(String itemName, String itemType, int itemId, int qty_inStock, double itemPrice){
        this.itemId = itemId
        this.itemName = itemName;
        this.itemType = itemType;
        this.qty_inStock = qty_inStock;
        this.itemPrice = itemPrice;




    }




}

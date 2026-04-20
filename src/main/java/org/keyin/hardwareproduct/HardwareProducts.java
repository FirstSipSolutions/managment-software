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
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.qty_inStock = qty_inStock;
        this.itemPrice = itemPrice;




    }

    // forgot to add the constructor that will write to the DB
    // this will include all the same variables

    public HardwareProducts(int itemId, String itemName, String itemType, double itemPrice, int qty_inStock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.qty_inStock = qty_inStock;
    }

    public HardwareProducts(String itemName, String itemType, double itemPrice, int qty_inStock){
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.qty_inStock = qty_inStock;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQty_inStock() {
        return qty_inStock;
    }

    public void setQty_inStock(int qty_inStock) {
        this.qty_inStock = qty_inStock;
    }
}

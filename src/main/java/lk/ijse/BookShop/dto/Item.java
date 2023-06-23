package lk.ijse.BookShop.dto;

public class Item {
    private String item_Id;
    private String itemName;
    private String MatiriyalType;
    private String discription;
    private int QtyOnHand;
    private double UnitPrice;


    public Item(String item_Id, String itemName, String matiriyalType, String discription, int qtyOnHand, double unitPrice) {
        this.item_Id = item_Id;
        this.itemName = itemName;
        MatiriyalType = matiriyalType;
        this.discription = discription;
        QtyOnHand = qtyOnHand;
        UnitPrice = unitPrice;
    }

    public Item() {
    }

    public String getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(String item_Id) {
        this.item_Id = item_Id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMatiriyalType() {
        return MatiriyalType;
    }

    public void setMatiriyalType(String matiriyalType) {
        MatiriyalType = matiriyalType;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_Id='" + item_Id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", MatiriyalType='" + MatiriyalType + '\'' +
                ", discription='" + discription + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}

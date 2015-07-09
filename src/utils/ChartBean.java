package utils;

/**
 * ��ChartView�е�ÿ��������?
 * 
 * @author YocnZhao
 * 
 */
public class ChartBean {
    private double price;
    private String date;

    public ChartBean(double price, String date) {
        this.price = price;
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ChartBean [price=" + price + ", date=" + date + "]";
    }

}

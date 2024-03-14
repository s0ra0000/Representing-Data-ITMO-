package utils;

public class Label {
    public char value;

    public void print() {
        System.out.println(value);
    }

    public Label(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}

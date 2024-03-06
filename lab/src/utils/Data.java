package utils;

import java.util.Arrays;
import java.util.Objects;

public class Data{
    private char[] name = new char[20]; // Приватное поле 'value' для хранения значения
    private char[] address = new char[50]; // Приватное поле 'address' для хранения адреса
    public Data(String address,String name) { // Конструктор класса с параметрами 'address' и 'value'
        setName(name.toCharArray()); // Инициализация поля 'value' переданным значением
        setAddress(address.toCharArray()) ;  // Инициализация поля 'address' переданным адресом
    }
    public Data(char[] address, char[] name){
        setName(name); // Инициализация поля 'value' переданным значением
        setAddress(address) ;  // Инициализация поля 'address' переданным адресом
    }
    // Getter


    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        if (name.length > this.name.length) {
            this.name = Arrays.copyOf(name, this.name.length);
        } else {
            this.name = Arrays.copyOf(name, name.length);
        }
    }

    public char[] getAddress() {
        return address;
    }

    public void setAddress(char[] address) {
        if (address.length > this.address.length) {
            this.address = Arrays.copyOf(address, this.address.length);
        } else {
            this.address = Arrays.copyOf(address, address.length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Arrays.equals(name, data.name) && Arrays.equals(address, data.address);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(address);
        return result;
    }

    @Override
    public String toString() {
        String valueString = new String(name); // Converts the character array to a string.
        String addressString = new String(address); // Converts the character array to a string.

        return "name:" + valueString + ", address:" + addressString;
    }
}
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
        for (int i = 0; i < name.length; i++){
            this.name[i] = name[i];
        }
    }

    public char[] getAddress() {
        return address;
    }

    public void setAddress(char[] address) {
        // Сам копируй
        for (int i = 0; i < address.length; i++){
            this.address[i] = address[i];

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        if(data.getNameLength() != getNameLength() || data.getAddressLength() != getAddressLength()) {
            return false;
        }

        for (int i = 0; i < getNameLength(); i++) {
            System.out.println("haaha");
            if (name[i] != data.name[i]) return false;
        }

        for (int i = 0; i < getAddressLength(); i++) {
            if (address[i] != data.address[i]) return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int result = Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(address);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("name: ");
        for (char c : name) {
            if (c != 0) {
                result.append(c);
            }
        }
        result.append(" , address: ");
        for (char c : address) {
            if (c != 0) {
                result.append(c);
            }
        }

        // Return the result as a string
        return result.toString();
    }

    public int getNameLength() {
        return name.length;
    }
    public int getAddressLength() {
        return address.length;
    }
}
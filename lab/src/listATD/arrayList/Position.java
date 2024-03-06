package listATD.arrayList;

public class Position {
    private int index; // Объявление переменной index для хранения индекса позиции

    // Constructor
    public Position(int index) {// Конструктор класса, принимает индекс позиции
        this.index = index;    // Инициализация переменной index переданным значением
    }

    // Getter
    public int getIndex() {// Геттер для переменной index
        return index;
    }
    public void setIndex(int index) { // Сеттер для переменной index
         this.index = index;
    }
    @Override
    public String toString() {  // Метод для получения строкового представления объекта
        return "Position: " + index;
    }
    @Override
    public boolean equals(Object o) { // Метод для сравнения текущего объекта с другим
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position Data = (Position) o;
        return (this.index == Data.index); // Сравнение индексов двух объектов Position
    }

}

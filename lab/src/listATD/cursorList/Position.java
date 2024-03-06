package listATD.cursorList;

import java.util.Objects;

public class Position {
    private int index;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return (this.index == position.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}

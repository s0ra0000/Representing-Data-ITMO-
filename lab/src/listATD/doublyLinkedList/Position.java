package listATD.doublyLinkedList;


import java.util.Objects;

public class Position {
    public Node node;

    public Position(Node node) { // Конструктор класса, принимает узел связанного списка
        this.node = node; // Инициализация переменной node переданным узлом
    }

    @Override
    public boolean equals(Object o) { // Переопределение метода equals для сравнения объектов Position
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(node, position.node); // Сравнение узлов двух объектов Position с помощью метода equals класса Objects
    }
}

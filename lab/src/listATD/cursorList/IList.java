package listATD.cursorList;

import utils.Data;

public interface IList {
    Position end();
    void insert(Data x, Position p);
    Position locate(Data x);
    Data retrieve(Position p);
    void delete(Position p);
    Position next(Position p);
    Position previous(Position p);
    void makenull();
    Position first();
    void printlist();
}

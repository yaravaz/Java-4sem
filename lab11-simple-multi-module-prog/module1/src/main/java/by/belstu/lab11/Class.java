package by.belstu.lab11;

public class Class {
    String _name;
    int _id;
    String _work;

    public Class(){}

    public Class(String name, int id, String work){
        _name = name;
        _id = id;
        _work = work;
    }

    @Override
    public String toString() {
        return _name + "," + _id + ", " + _work + "\n";
    }
}

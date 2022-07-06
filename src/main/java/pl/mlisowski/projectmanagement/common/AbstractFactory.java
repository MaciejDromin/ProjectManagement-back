package pl.mlisowski.projectmanagement.common;

public interface AbstractFactory<T, S> {

    S from(T toConvert);

    T to(S toConvert);

}

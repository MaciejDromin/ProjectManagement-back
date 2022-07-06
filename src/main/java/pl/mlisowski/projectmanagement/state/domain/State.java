package pl.mlisowski.projectmanagement.state.domain;

public interface State {

    State updateState(State state);

}

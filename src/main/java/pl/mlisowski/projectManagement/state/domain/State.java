package pl.mlisowski.projectManagement.state.domain;

public interface State {

    State updateState(State state);

}

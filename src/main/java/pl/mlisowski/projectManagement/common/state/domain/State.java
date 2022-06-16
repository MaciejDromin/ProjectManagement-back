package pl.mlisowski.projectManagement.common.state.domain;

public interface State {

    State updateState(State state);

}

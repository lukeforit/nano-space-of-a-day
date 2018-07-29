package me.lukeforit.spaceofaday.data.source;

public class RepositoryInteractor<T> {
    protected SpaceRepository repository;

    public RepositoryInteractor(SpaceRepository repository) {
        this.repository = repository;
    }
}

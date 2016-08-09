package sunnyxiaobai5.core.base;

public abstract class BaseDTO<T> {

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDTO)) return false;

        BaseDTO<?> baseDTO = (BaseDTO<?>) o;

        return !(getId() != null ? !getId().equals(baseDTO.getId()) : baseDTO.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}

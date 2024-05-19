package tripDemo.dictionaries;

public enum ServiceEnum {
    TRIP;

    public IPathEnum[] getPathEnumList() {
        switch (this) {
            case TRIP : return TripPathEnum.values();
            default:throw new IllegalArgumentException();
        }
    }
}

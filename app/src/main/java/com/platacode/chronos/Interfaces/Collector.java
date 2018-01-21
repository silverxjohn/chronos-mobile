package com.platacode.chronos.Interfaces;

import java.util.List;

public interface Collector<T> {
    public void collect(List<T> items);
}

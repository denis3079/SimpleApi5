package ru.mtuci.simpleapi1.dto;

import java.util.Objects;

public class Status {
    private String hostName;

    public Status(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(hostName, status.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName);
    }
}

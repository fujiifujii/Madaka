package com.example.madaka.util;

public enum ReasonType {
	DELAYS("電車遅延"),
	ILLNESS("体調不良"),
	OTHER("その他");

    private final String label;

    ReasonType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

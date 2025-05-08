package com.example.hacktanton1.domain.model;

public enum models {
    DEEPSEEK_V3_0324("deepseek/DeepSeek-V3-0324"),
    OPENAI("openai/gpt-4.1-mini"),
    META("meta/Llama-4-Maverick-17B-128E-Instruct-FP8");

    private final String modelName;

    models(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }
}

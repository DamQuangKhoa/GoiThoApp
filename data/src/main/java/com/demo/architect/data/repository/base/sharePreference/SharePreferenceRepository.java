package com.demo.architect.data.repository.base.sharePreference;

public interface SharePreferenceRepository {

    String readMyPreference();

    void writeMyPreference(String value);

}

package ua.ita.smartcarservice.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import ua.ita.smartcarservice.service.HashService;

public class HashServiceImpl implements HashService {
    @Override
    public String makeHash(Long id, String username, String filename) {
        return DigestUtils.md5Hex(id.toString()+username+filename);
    }
}

package com.quan.datn.security;


import com.quan.datn.common.Constants;
import com.quan.datn.model.database.BenhNhan;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.LocalDateTime;

import java.util.Date;

public class AuthenToken {

    public static String generateToken(BenhNhan benhNhan) {
        return Jwts.builder().setId(benhNhan.getId() + "")
                .setIssuedAt(LocalDateTime.now().toDate())
                .setSubject(String.valueOf(benhNhan.getId()))
                .claim("MaBN",benhNhan.getMaBN())
                .claim("CMND", benhNhan.getCmnd())
                .claim("display_name",benhNhan.getHo() + benhNhan.getTen())
                .setIssuer("no comment")
                .setExpiration(new Date(LocalDateTime.now().toDate().getTime() + 30*24*60*60*1000L))
                .signWith(SignatureAlgorithm.HS512, Constants.KEY_TOKEN.getBytes())
                .compact();
    }

    public static String getMaBNFromToken(String token){
        return Jwts.parser()
                .setSigningKey(Constants.KEY_TOKEN.getBytes())
                .parseClaimsJws(token)
                .getBody().get("MaBN").toString();
    }

}

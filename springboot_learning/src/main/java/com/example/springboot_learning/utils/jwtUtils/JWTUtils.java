package com.example.springboot_learning.utils.jwtUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    /**
     * 令牌秘钥
     */
    private static final String SECRET = "token@#$springboot_learning";

    /**
     * 用户ID字段
     */
    private static final String DETAILS_USER_ID = "userId";

    /**
     * 用户名字段
     */
    private static final String DETAILS_USERNAME = "userName";

    private static final Integer Expires_Days = 30;



    /**
     * 创建一个包含映射数据的JWT令牌。
     *
     * @param map 包含令牌中声明的数据映射。键值对表示声明的名称和值。
     * @return 生成的JWT令牌字符串。
     */
    public static String createToken(Map<String, String> map) {
        //说一下Jwt 以及下述代码中使用的代码
        //JWT.create().withHeader() 方法是用来创建 jwt字符串的“头”的
        //JWT.create().withClaim() 创建负载
        //JWT.create().sign() 设置签名
        //JWT.create().withExpiresAt(date) 设置过期时间
        //jwtVerifier.verify() 获取解码类
        //Algorithm.HMAC256 加密算法

        // 创建JWT令牌的构建器
        JWTCreator.Builder builder = JWT.create();

        // 遍历映射，为每个键值对添加声明到令牌构建器
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        // 获取当前日期并添加7天，设置令牌的过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, Expires_Days);
        builder.withExpiresAt(instance.getTime());

        // 使用HMAC256算法和SECRET签名令牌，并返回生成的令牌字符串
        String token = builder.sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET));

        return token;
    }


    /**
     * 验证JWT的合法性。
     * 使用HMAC256算法和预定义的SECRET对JWT进行验证，确保JWT未被篡改且在有效期内。
     *
     * @param token 待验证的JWT令牌。
     * @return 经过验证的JWT对象，包含JWT的详细信息。
     */
    public static DecodedJWT verify(String token) {
        // 使用JWT库的API构建JWT验证要求，指定验证算法为HMAC256，并使用SECRET作为验证密钥
        // 然后对传入的token进行验证，返回验证结果
        DecodedJWT decodedJWT = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET)).build().verify(token);
        return decodedJWT;
    }

    /**
     * 根据令牌获取JWT头部信息。
     * <p>
     * 该方法通过解码JWT令牌来获取其中的头部信息。JWT（JSON Web Token）是一种紧凑的
     * 用于在各方之间安全地传输信息的令牌格式。头部通常包含令牌的类型（JWT）和加密算法。
     * 使用这个方法，你可以获取到这些重要信息，以便进行进一步的验证或处理。
     *
     * @param token JWT令牌字符串。这是由三部分组成的字符串，每部分之间用点（.）分隔，
     *              包含头部、载荷和签名。
     * @return 返回JWT令牌的头部信息。头部信息以JSON格式表示，包含了令牌的类型和加密算法等。
     */
    public static String getHeaderByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        // 解码JWT令牌并获取头部信息
        return JWT.decode(token).getHeader();
    }

    /**
     * 根据令牌获取负载信息。
     * <p>
     * 该方法通过解码JWT令牌来获取其中的负载信息。JWT（JSON Web Token）是一种紧凑的
     * 用于在各方之间安全地传输信息的令牌格式。负载信息通常包含关于令牌的声明，
     * 如颁发者、受众、有效时间等。
     *
     * @param token JWT令牌字符串。这是经过编码的令牌，包含头部、负载和签名三部分。
     * @return 返回解码后的JWT令牌的负载部分。负载部分是JWT中包含实际声明的部分，
     *         它们可以是声明、权限或其他相关信息。
     * @see JWT#decode(String) JWT解码方法，用于解析JWT令牌。
     */
    public static String getPayloadByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getPayload();
    }

    // 根据token获取claims
    public static String getClaimsByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getClaims().toString();
    }

    // 根据token获取claims中某个key的值
    public static String getClaimsByToken(String token, String key){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getClaim(key).asString();
    }

    // 根据token获取claims中userName的值
    public static String getUserNameByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getClaim(DETAILS_USERNAME).asString();
    }

    // 根据token获取claims中userId的值
    public static String getUserIdByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getClaim(DETAILS_USER_ID).asString();
    }


    /**
     * 根据令牌获取签名。
     * <p>
     * 该方法通过解码JWT令牌来获取其中的签名信息。签名是JWT安全性的重要组成部分，
     * 它用于验证JWT的完整性和来源。使用此方法可以从令牌中提取签名，以便进行验证或其他用途。
     *
     * @param token 待解码的JWT令牌。这个参数不应该为空，否则解码过程会失败。
     * @return 返回解码后的JWT令牌的签名。如果令牌解码失败，将抛出异常。
     */
    public static String getSignatureByToken(String token){
        if (token == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        return JWT.decode(token).getSignature();
    }


}

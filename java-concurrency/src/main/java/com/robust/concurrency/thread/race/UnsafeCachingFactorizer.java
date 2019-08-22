package com.robust.concurrency.thread.race;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/16 10:48
 * @Version: 1.0
 */
public class UnsafeCachingFactorizer{
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors =new AtomicReference<BigInteger[]>();
    /*public void service（
    ServletRequest req, ServletResponse
    resp）

    {
        BigInteger i = extractFromRequest（req）；
        if（i.equals（lastNumber.get（）））
        encodeIntoResponse（resp, lastFactors.get（））；
else{
        BigInteger[] factors = factor（i）；
        lastNumber.set（i）；
        lastFactors.set（factors）；
        encodeIntoResponse（resp, factors）；
    }
    }*/
}

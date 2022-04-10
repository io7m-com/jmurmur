/*
 * Copyright © 2014 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jmurmur;

import com.io7m.junreachable.UnreachableCodeException;

/**
 * <p>
 * An implementation of the public domain <a href="https://en.wikipedia.org/wiki/MurmurHash">Murmur3</a>
 * hash function.
 * </p>
 */

public final class Murmur3
{
  private static final int C1 = 0xcc9e2d51;
  private static final int C2 = 0x1b873593;

  /**
   * The default seed if one is not explicitly given. Just a large prime, no significance.
   */

  public static final int DEFAULT_SEED = 0x12188101;

  private static int finalMix(
    final int x,
    final int length)
  {
    int h1 = x;
    h1 ^= length;
    h1 ^= h1 >>> 16;
    h1 *= 0x85ebca6b;
    h1 ^= h1 >>> 13;
    h1 *= 0xc2b2ae35;
    h1 ^= h1 >>> 16;
    return h1;
  }

  /**
   * Hash the given integer value using the default seed (@link {@link #DEFAULT_SEED}).
   *
   * @param x The integer value.
   *
   * @return A hash value.
   */

  public static int hashInt(
    final int x)
  {
    return Murmur3.hashIntWithSeed(x, Murmur3.DEFAULT_SEED);
  }

  /**
   * Hash the given integer value using the given seed to randomize the results.
   *
   * @param x    The integer value.
   * @param seed The seed value.
   *
   * @return A hash value.
   */

  public static int hashIntWithSeed(
    final int x,
    final int seed)
  {
    final int k1 = Murmur3.mixK1(x);
    final int h1 = Murmur3.mixH1(seed, k1);
    return Murmur3.finalMix(h1, 4);
  }

  /**
   * Hash the given long value using the default seed (@link {@link #DEFAULT_SEED}).
   *
   * @param x The long value.
   *
   * @return A hash value.
   */

  public static int hashLong(
    final long x)
  {
    return Murmur3.hashLongWithSeed(x, Murmur3.DEFAULT_SEED);
  }

  /**
   * Hash the given long value using the given seed to randomize the results.
   *
   * @param x    The long value.
   * @param seed The seed value.
   *
   * @return A hash value.
   */

  public static int hashLongWithSeed(
    final long x,
    final int seed)
  {
    final int low = (int) x;
    final int high = (int) (x >>> 32);
    int k1 = Murmur3.mixK1(low);
    int h1 = Murmur3.mixH1(seed, k1);
    k1 = Murmur3.mixK1(high);
    h1 = Murmur3.mixH1(h1, k1);
    return Murmur3.finalMix(h1, 8);
  }

  private static int mixH1(
    final int x,
    final int k1)
  {
    int h1 = x;
    h1 ^= k1;
    h1 = Integer.rotateLeft(h1, 13);
    h1 = h1 * 5 + 0xe6546b64;
    return h1;
  }

  private static int mixK1(
    final int x)
  {
    int k1 = x;
    k1 *= Murmur3.C1;
    k1 = Integer.rotateLeft(k1, 15);
    k1 *= Murmur3.C2;
    return k1;
  }

  private Murmur3()
  {
    throw new UnreachableCodeException();
  }
}

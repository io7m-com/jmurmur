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

package com.io7m.jmurmur.tests;

import com.io7m.jmurmur.Murmur3;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public final class Murmur3Test
{
  @Test
  public void testQuality()
  {
    final Random r = new Random();
    final Set<Integer> i = new HashSet<Integer>();
    final Set<Integer> c = new HashSet<Integer>();

    for (int index = 0; index < Math.pow(2, 20); ++index) {
      i.add(r.nextInt());
    }

    for (final Integer k : i) {
      c.add(Murmur3.hashInt(k.intValue()));
    }

    System.out.println("Size(i): " + i.size());
    System.out.println("Size(c): " + c.size());

    Assert.assertTrue(c.size() >= i.size() - 10);
  }

  @Test
  public void testQualitySeeded()
  {
    final Random r = new Random();
    final Set<Integer> i = new HashSet<Integer>();
    final Set<Integer> c = new HashSet<Integer>();

    for (int index = 0; index < Math.pow(2, 20); ++index) {
      i.add(r.nextInt());
    }

    final int seed = r.nextInt();
    for (final Integer k : i) {
      c.add(Murmur3.hashIntWithSeed(k.intValue(), seed));
    }

    System.out.println("Size(i): " + i.size());
    System.out.println("Size(c): " + c.size());

    Assert.assertTrue(c.size() >= i.size() - 10);
  }

  @Test
  public void testQualityLong()
  {
    final Random r = new Random();
    final Set<Long> i = new HashSet<Long>();
    final Set<Integer> c = new HashSet<Integer>();

    for (int index = 0; index < Math.pow(2, 20); ++index) {
      i.add(r.nextLong());
    }

    for (final Long k : i) {
      c.add(Murmur3.hashLong(k.longValue()));
    }

    System.out.println("Size(i): " + i.size());
    System.out.println("Size(c): " + c.size());

    Assert.assertTrue(c.size() >= i.size() - 250);
  }

  @Test
  public void testQualitySeededLong()
  {
    final Random r = new Random();
    final Set<Integer> i = new HashSet<Integer>();
    final Set<Integer> c = new HashSet<Integer>();

    for (int index = 0; index < Math.pow(2, 20); ++index) {
      i.add(r.nextInt());
    }

    final int seed = r.nextInt();
    for (final Integer k : i) {
      c.add(Murmur3.hashIntWithSeed(k.intValue(), seed));
    }

    System.out.println("Size(i): " + i.size());
    System.out.println("Size(c): " + c.size());

    Assert.assertTrue(c.size() >= i.size() - 250);
  }

  @Test
  public void testDistribution()
  {
    final int r[] = new int[1024];

    for (int index = 0; index < 1024 * 1000; ++index) {
      final int h = Murmur3.hashInt(index);
      ++r[Math.abs(h) % 1024];
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    long sum = 0;

    for (int index = 0; index < 1024; ++index) {
      final int count = r[index];
      sum += count;
      min = Math.min(min, count);
      max = Math.max(max, count);
    }

    System.out.println("Minimum collisions: " + min);
    System.out.println("Maximum collisions: " + max);
    System.out.println("Average collisions: " + sum / 1024);
  }

  @Test
  public void testSeeded()
  {
    for (int index = 0; index < 1024; ++index) {
      final int h0 = Murmur3.hashInt(index);
      final int h1 = Murmur3.hashIntWithSeed(index, 23);
      final int h2 = Murmur3.hashIntWithSeed(index, 51);
      Assert.assertNotEquals(h0, h1);
      Assert.assertNotEquals(h1, h2);
      Assert.assertNotEquals(h0, h2);
    }
  }

  @Test
  public void testDistributionLong()
  {
    final int r[] = new int[1024];

    for (int index = 0; index < 1024 * 1000; ++index) {
      final int h = Murmur3.hashLong(index);
      ++r[Math.abs(h) % 1024];
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    long sum = 0;

    for (int index = 0; index < 1024; ++index) {
      final int count = r[index];
      sum += count;
      min = Math.min(min, count);
      max = Math.max(max, count);
    }

    System.out.println("Minimum collisions: " + min);
    System.out.println("Maximum collisions: " + max);
    System.out.println("Average collisions: " + sum / 1024);
  }

  @Test
  public void testSeededLong()
  {
    for (int index = 0; index < 1024; ++index) {
      final int h0 = Murmur3.hashLong(index);
      final int h1 = Murmur3.hashLongWithSeed(index, 23);
      final int h2 = Murmur3.hashLongWithSeed(index, 51);
      Assert.assertNotEquals(h0, h1);
      Assert.assertNotEquals(h1, h2);
      Assert.assertNotEquals(h0, h2);
    }
  }

  @Test
  public void testImage()
    throws Exception
  {
    final File f0 = File.createTempFile("jmurmur-image-A-", ".dat");
    final File f1 = File.createTempFile("jmurmur-image-B-", ".dat");
    final File f2 = File.createTempFile("jmurmur-image-C-", ".dat");

    System.out.println("Image 0: " + f0);
    System.out.println("Image 1: " + f1);
    System.out.println("Image 2: " + f2);

    final FileOutputStream fs0 = new FileOutputStream(f0);
    final FileOutputStream fs1 = new FileOutputStream(f1);
    final FileOutputStream fs2 = new FileOutputStream(f2);

    for (int y = 0; y < 256; ++y) {
      for (int x = 0; x < 256; ++x) {
        final int i = (y * 256) + x;
        final int h0 = Murmur3.hashIntWithSeed(i, 1777);
        final int h1 = Murmur3.hashIntWithSeed(i, 7993);
        final int h2 = Murmur3.hashIntWithSeed(i, 8581);

        final float hf0 = (float) h0 / (float) Integer.MAX_VALUE;
        final float hf1 = (float) h1 / (float) Integer.MAX_VALUE;
        final float hf2 = (float) h2 / (float) Integer.MAX_VALUE;

        fs0.write((int) Math.abs(hf0 * 256));
        fs1.write((int) Math.abs(hf1 * 256));
        fs2.write((int) Math.abs(hf2 * 256));
      }
    }

    fs0.flush();
    fs1.flush();
    fs2.flush();

    fs0.close();
    fs1.close();
    fs2.close();
  }
}

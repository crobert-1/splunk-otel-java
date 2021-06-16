/*
 * Copyright Splunk Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.splunk.opentelemetry.profiler.context;

import jdk.jfr.consumer.RecordedThread;

/** A wrapper for a RecordedEvent that may or may not have accompanying span information. */
public class StackToSpanLinkage {
  private final String rawStack;
  private final SpanLinkage spanLinkage;

  StackToSpanLinkage(String rawStack, SpanLinkage spanLinkage) {
    this.rawStack = rawStack;
    this.spanLinkage = spanLinkage;
  }

  public boolean hasSpanInfo() {
    return getSpanId() != null;
  }

  public String getRawStack() {
    return rawStack;
  }

  public String getTraceId() {
    return spanLinkage.getTraceId();
  }

  public String getSpanId() {
    return spanLinkage.getSpanId();
  }

  public RecordedThread getSpanStartThread() {
    return spanLinkage.getRecordedThread();
  }

  static StackToSpanLinkage withoutLinkage(String rawStack) {
    return new StackToSpanLinkage(rawStack, SpanLinkage.NONE);
  }
}
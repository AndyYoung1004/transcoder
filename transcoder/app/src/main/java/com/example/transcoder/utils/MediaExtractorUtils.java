/*
 * Copyright (C) 2014 Yuya Tanaka
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
package com.example.transcoder.utils;

import android.media.MediaExtractor;
import android.media.MediaFormat;

public class MediaExtractorUtils {

    private MediaExtractorUtils() {
    }

    public static class TrackResult {

        private TrackResult() {
        }

        public int mVideoTrackIndex;
        public String mVideoTrackMime;
        public MediaFormat mVideoTrackFormat;
    }

    public static TrackResult getFirstVideoTrack(MediaExtractor extractor) {
        TrackResult trackResult = new TrackResult();
        trackResult.mVideoTrackIndex = -1;
        int trackCount = extractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            MediaFormat format = extractor.getTrackFormat(i);
            String mime = format.getString(MediaFormat.KEY_MIME);
            if (trackResult.mVideoTrackIndex < 0 && mime.startsWith("video/")) {
                trackResult.mVideoTrackIndex = i;
                trackResult.mVideoTrackMime = mime;
                trackResult.mVideoTrackFormat = format;
                break;
            }
        }
        if (trackResult.mVideoTrackIndex < 0) {
            throw new IllegalArgumentException("extractor does not contain video and/or tracks.");
        }
        return trackResult;
    }
}

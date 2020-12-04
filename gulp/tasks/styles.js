"use strict";

import { paths } from "../gulp-configure";
import gulp from "gulp";
import rename from "gulp-rename";
import sass from "gulp-sass";
import mincss from "gulp-clean-css";
import groupmedia from "gulp-group-css-media-queries";
import autoprefixer from "gulp-autoprefixer";
import sourcemaps from "gulp-sourcemaps";
import plumber from "gulp-plumber";
import browsersync from "browser-sync";
import debug from "gulp-debug";

gulp.task("styles", () => {
    return gulp.src(paths.styles.src)
        .pipe(sourcemaps.init())
        .pipe(plumber())
        .pipe(sass())
        .pipe(groupmedia())
        .pipe(autoprefixer({
            cascade: false,
            grid: true
        }))
        .pipe(mincss({
            compatibility: "ie8", level: {
                1: {
                    specialComments: 0,
                    removeEmpty: true,
                    removeWhitespace: true
                },
                2: {
                    mergeMedia: true,
                    removeEmpty: true,
                    removeDuplicateFontRules: true,
                    removeDuplicateMediaBlocks: true,
                    removeDuplicateRules: true,
                    removeUnusedAtRules: false
                }
            }
        }))
        .pipe(rename({
            suffix: ".min"
        }))
        .pipe(plumber.stop())
        .pipe(sourcemaps.write("./maps/"))
        .pipe(gulp.dest(paths.styles.static))
        .pipe(gulp.dest(paths.styles.target))
        .pipe(debug({
            "title": "CSS files"
        }))
        .on("end", browsersync.reload);
});
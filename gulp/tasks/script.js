"use strict";

import { paths } from "../gulp-configure";
import webpack from "webpack";
import webpackStream from "webpack-stream";
import gulp from "gulp";
import rename from "gulp-rename";
import browsersync from "browser-sync";
import debug from "gulp-debug";

const webpackConfig = require('../../webpack.config.js');
webpackConfig.mode = "development";
webpackConfig.devtool = "source-map";

gulp.task("scripts", () => {
    return gulp.src(paths.scripts.src)
        .pipe(webpackStream(webpackConfig), webpack)
        .pipe(rename({
            suffix: ".min"
        }))
        .pipe(gulp.dest(paths.scripts.static))
        .pipe(gulp.dest(paths.scripts.target))
        .pipe(debug({
            "title": "JS files"
        }))
        .pipe(browsersync.stream());
});
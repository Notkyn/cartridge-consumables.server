"use strict";

import gulp from "gulp";

const   requireDir = require('require-dir');

requireDir("./gulp/tasks/");

export const development = gulp.series("clean",
    gulp.parallel(["thymeleaf", "styles", "fonts", "scripts", "images"]),
    gulp.parallel("server"));

export default development;
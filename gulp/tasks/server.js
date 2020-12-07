"use strict";

import { paths } from "../gulp-configure";
import { server } from "../gulp-configure";
import gulp from "gulp";
import browsersync from "browser-sync";

gulp.task("server", () => {
    browsersync.init({
        proxy: server.proxy,
        //server: server.path,
        port: server.port,
        notify: false
    });

    gulp.watch(paths.thymeleaf.watch, gulp.parallel("thymeleaf"));
    gulp.watch(paths.styles.watch, gulp.parallel("styles"));
    gulp.watch(paths.scripts.watch, gulp.parallel("scripts"));
    gulp.watch(paths.images.watch, gulp.parallel("images"));
    gulp.watch(paths.fonts.watch, gulp.parallel("fonts"));
});
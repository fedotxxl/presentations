var gulp = require('gulp');
var extender = require('gulp-html-extend');
var runSequence = require('run-sequence');
var path = require('path');
var rimraf = require('gulp-rimraf');
var browserSync = require('browser-sync').create();
var watch = require('gulp-watch');

var paths = {
    target: {
        root: "./build",
        static: "./build"
    }
};

gulp.task('watch', ['browser-sync'], function () {
    watch(['./**/*.*'], function() {
        runSequence('browser-sync.reload')
    });
});

gulp.task('browser-sync.reload', function() {
    browserSync.reload();
});

gulp.task('browser-sync', function() {
    browserSync.init({
        ghostMode: false,
        server: {
            baseDir: "./"
        }
    });
});
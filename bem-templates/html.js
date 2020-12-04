'use strict';

var EOL = require('os').EOL;

module.exports = function(entity, naming) {
    return [
        '<!DOCTYPE html>',
        '<html xmlns="http://www.w3.org/1999/xhtml"',
        'xmlns:th="http://www.thymeleaf.org"',
        'lang="en">',
        '<div th:fragment="' + naming.stringify(entity) + '" class="' + naming.stringify(entity) + '">',
        '',
        '</div>',
        '</html>'

    ].join(EOL);
};
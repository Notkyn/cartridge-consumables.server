'use strict';

var EOL = require('os').EOL;

module.exports = function(entity, naming) {
    return [
        '.' + naming.stringify(entity) + ' {',
        '   padding: 0;',
        '}',
        ''
    ].join(EOL);
};
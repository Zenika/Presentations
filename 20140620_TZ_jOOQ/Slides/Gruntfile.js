module.exports = function(grunt) {

    grunt.initConfig({
        connect: {
            options: {
                base: './',
                open: true,
                hostname: 'localhost',
                port: 8000,
                livereload: 32729
            },
            server: {},
            keepalive: {
                options: { keepalive: true },
            },
        },
        watch: {
            options: {
                livereload: 32729,
            },
            content: {
                files: ['*.md', 'slides.json'],
            },
            ressources: {
                files: 'ressources/**',
            },
            reveal: {
                files: 'reveal/**',
            },
            index: {
                files: 'index.html',
            },
            gruntfile: {
                files: 'Gruntfile.js',
            },
        },
    });

    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('serve', ['connect:server', 'watch']);
    grunt.registerTask('default', ['serve']);

};

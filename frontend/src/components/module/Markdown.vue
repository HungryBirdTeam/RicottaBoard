<template>
    <div id="markdown">
        <div class="d-flex justify-content-between changer">
            <h3>문서</h3>
            <button type="button" class="btn btn-dark" @click="MarkChange" v-if="isMark">편집하기</button>
            <button type="button" class="btn btn-dark" @click="MarkChange" v-if="!isMark">문서보기</button>
        </div>
        <div class="d-flex">
            <textarea v-if="!isMark" :value="text" @input="update"></textarea>
            <div class="editor" v-if="isMark" v-html="compiledMarkdown"></div>
        </div>
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="https://unpkg.com/lodash@4.16.0"></script>
<script>
export default {
    el: '#markdown',
    data() {
        return { 
            text: '',
            isMark: false,
        }
    },
    computed: {
        compiledMarkdown: function () {
            const marked = require('marked');
            console.log(this.text)
            return marked(this.text)
        }
    },
    methods: {
        update: _.debounce(function (e) {
        this.text = e.target.value
        }, 300),
        MarkChange() {
            this.isMark = !this.isMark
        }
    }
}
</script>

<style>
html, body, #markdown {
    margin: 0;
    width: 600px;
    height: 900px;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
}

.changer {
    width: 600px;
    height: 50px;
}

textarea, #markdown div {
    display: inline-block;
    vertical-align: top;
    box-sizing: border-box;
    width: 600px;
    height: 850px;
}

textarea {
    border: none;
    border-right: 1px solid #ccc;
    resize: none;
    outline: none;
    background-color: #f6f6f6;
    font-size: 14px;
    font-family: 'Monaco', courier, monospace;
}

.editor {
    font-family: 'Helvetica Neue', Arial, sans-serif !important;
}

code {
    color: #f66;
}
</style>
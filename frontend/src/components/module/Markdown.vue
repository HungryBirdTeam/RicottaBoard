<template>
    <div id="markdown" class="MoveableBox">
        <div class="row changer d-flex justify-content-between">
            <input type="text" placeholder="제목" class="col-10" v-model="title">
            <button type="button" class="btn btn-dark col-2" @click="MarkChange" v-if="isMark">편집하기</button>
            <button type="button" class="btn btn-dark c0l-2" @click="MarkChange" v-if="!isMark">문서보기</button>
        </div>
        <div class="row">
            <textarea v-if="!isMark" :value="text" @input="update"></textarea>
            <div class="editor" v-if="isMark" v-html="compiledMarkdown"></div>
        </div>
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="lodash.js"></script>
<script>
import _ from "lodash"

export default {
    data() {
        return {
            title: '', 
            text: '',
            isMark: false,
        }
    },
    props: {
        markdown: Object,
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
    background-color: #f6f6f6;
}

.changer {
    width: 600px;
    height: 50px;
    border: 1px solid #ccc;
    background-color: #f6f6f6;
}

.editor {
    border: 1px solid #ccc;
    display: inline-block;
    vertical-align: top;
    box-sizing: border-box;
    width: 600px;
    height: 850px;
    background-color: #f6f6f6;
}

textarea {
    border: none;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    resize: none;
    outline: none;
    background-color: #f6f6f6;
    font-size: 14px;
    display: inline-block;
    vertical-align: top;
    box-sizing: border-box;
    width: 600px;
    height: 850px;
    font-family: 'Monaco', courier, monospace;
}

.editor {
    font-family: 'Helvetica Neue', Arial, sans-serif;
}

code {
    color: #f66;
}
</style>
<template>
    <div id="EditorMain" class="MoveableBox editor">
        <div v-if="editor.isHidden">
            <div class="row m-0">
                <v-btn
                    color="blue-grey lighten-4"
                    @click="changeHidden"
                    class="my-auto mx-1"
                    >
                    <v-icon
                        center
                        light
                    >
                        mdi-arrow-up-drop-circle-outline
                    </v-icon>
                </v-btn>
                <div v-if="editor.title" class="align-middle my-auto ml-1"> {{ editor.title }} </div>
                <div v-if="!editor.title" class="align-middle my-auto ml-1"> 제목 없음 </div>                
            </div>
        </div>
        <div v-if="!editor.isHidden">
            <div class="title row m-0">
                <div class="col-10 p-0 m-0">
                    <div class="semititle row m-0">
                        <v-btn
                        color="blue-grey lighten-4"
                        @click="changeHidden"
                        class="my-auto mx-1"
                        >
                            <v-icon
                                center
                                dark
                            >
                                mdi-arrow-down-drop-circle
                            </v-icon>
                        </v-btn>
                        <input type="text" placeholder="제목" v-model="editor.title" class="ml-1">
                    </div>
                </div>
                <v-btn
                    :loading="isLoading"
                    :disabled="isLoading"
                    color="blue-grey"
                    class="m-auto  white--text"
                    @click="saveEditor"
                    >
                    저장하기
                    <v-icon
                        right
                        dark
                    >
                        mdi-cloud-download
                    </v-icon>
                </v-btn>
            </div>
            <Editor        
                height="500px"
                :initialValue="editor.text"
                ref="toastuiEditor"
                @change = "onEditorChange"
                class="bg-white"
            />
        </div>
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="lodash.js"></script>
<script>
import _ from "lodash"
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';
import { saveAs } from 'file-saver';

export default {
    data() {
        return {
            isLoading: false,
        }
    },
    components: {
        Editor
    },
    props: {
        editor: Object,
    },
    created() {
        console.log("내부확인",this)
    },
    watch: {
        text: function() {
            this.textChange()
        }
    },
    methods: {
        onEditorChange() {
            this.editor.text = this.$refs.toastuiEditor.invoke("getMarkdown");
            console.log('editor text!', this.editor.text)
        },
        saveEditor() {     
            this.isLoading = true;
            var FileSaver = require ('file-saver');
            var blob = new Blob([this.editor.text], { type : "text / plain; charset = utf-8" });
            var name = "untitle";
            if (this.editor.title) {
                name = this.editor.title
            };
            console.log(name)
            FileSaver.saveAs (blob, name+".md");
            setTimeout(() => (this.isLoading = false), 1000);
        },
        textChange() {
            if (this.editor.text != this.$refs.toastuiEditor.invoke("getMarkdown")) {
                this.$refs.toastuiEditor.invoke("setMarkdown", this.editor.text)
                console.log('text', this.editor.text)
                console.log('editor', this.$refs.toastuiEditor.invoke("getMarkdown"))
            }
        },
        changeHidden() {
            this.editor.isHidden = !this.editor.isHidden
            console.log(this.editor.isHidden)
            console.log(this)
            console.log(this.editor, this._props.editor)
        }
    }
}
</script>

<style scoped>
html, body, #EditorMain {
    margin: 0;
    width: 860px;
    height: 610px;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
    background-color: #f6f6f6;
    /* ::v-deep {
        margin: 0;
        font-family: 'Helvetica Neue', Arial, sans-serif;
    } */
}

.title {
    width: 800px;
    height: 50px;
    border: 1px solid #ccc;
    background-color: #f6f6f6;
}

.semititle {
    width: 666.66px;
    height: 50px;
}
</style>
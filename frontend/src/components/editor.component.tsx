
import { TwitterPicker } from 'react-color';
import { htmlToProsemirrorNode } from 'remirror';
import {
    Remirror,
    ThemeProvider,
    ToggleBoldButton,
    ToggleBlockquoteButton,
    ToggleCodeBlockButton,
    ToggleItalicButton,
    ToggleStrikeButton,
    ToggleSubscriptButton,
    ToggleSuperscriptButton,
    ToggleUnderlineButton,
    CreateTableButton,
    ListButtonGroup,
    HeadingLevelButtonGroup,
    CalloutTypeButtonGroup,
    DropdownButton,
    CommandButtonGroup,
    CommandMenuItem,
    Toolbar,
    useActive,
    useCommands,
    useRemirror
} from '@remirror/react';
import { FileExtension } from '@remirror/extension-file';
import { TableExtension } from '@remirror/extension-react-tables';
import { BoldExtension, BulletListExtension, OrderedListExtension, TaskListExtension } from 'remirror/extensions';
import { BlockquoteExtension } from 'remirror/extensions';
import { CalloutExtension } from 'remirror/extensions';
import { CodeBlockExtension } from 'remirror/extensions';
import { DropCursorExtension } from 'remirror/extensions';
import { FontFamilyExtension } from 'remirror/extensions';
import { FontSizeExtension } from 'remirror/extensions';
import { HeadingExtension } from 'remirror/extensions';
import { HistoryExtension } from 'remirror/extensions';
import { ImageExtension } from 'remirror/extensions';
import { ItalicExtension } from 'remirror/extensions';
import { LinkExtension } from 'remirror/extensions';
import { MarkdownExtension } from 'remirror/extensions';
import { StrikeExtension } from 'remirror/extensions';
import { SubExtension } from 'remirror/extensions';
import { SupExtension } from 'remirror/extensions';
import { UnderlineExtension } from 'remirror/extensions';

import css from 'refractor/lang/css.js';
import javascript from 'refractor/lang/javascript.js';
import json from 'refractor/lang/json.js';
import markdown from 'refractor/lang/markdown.js';
import typescript from 'refractor/lang/typescript.js';

import 'remirror/styles/all.css';

const extensions = () => [
    new BoldExtension({}),
    new BlockquoteExtension(),
    new CalloutExtension({}),
    new CodeBlockExtension({ supportedLanguages: [css, javascript, json, markdown, typescript] }),
    new FileExtension({}),
    new DropCursorExtension({}),
    new FontFamilyExtension(),
    new FontSizeExtension({ defaultSize: '16', unit: 'px' }),
    new HeadingExtension({}),
    new HistoryExtension({}),
    new ImageExtension({
        enableResizing: true
    }),
    new ItalicExtension({}),
    new LinkExtension({ autoLink: true }),
    new BulletListExtension({}),
    new OrderedListExtension({}),
    new TaskListExtension(),
    new MarkdownExtension({}),
    new TableExtension({}),
    new StrikeExtension({}),
    new SubExtension({}),
    new SupExtension({}),
    new UnderlineExtension({})
];

const FONT_FAMILIES: Array<[React.CSSProperties['fontFamily'], string]> = [
    ['serif', 'Serif'],
    ['sans-serif', 'San serif'],
    ['cursive', 'Cursive'],
    ['fantasy', 'Fantasy'],
];

const FONT_SIZES = ['8', '10', '12', '14', '16', '18', '24', '30'];

export const ViraEditor = (): JSX.Element => {
    const { manager, state, onChange } = useRemirror({
        extensions: extensions,
        content: '<p>Text in <b>bold</b></p>',
        stringHandler: htmlToProsemirrorNode,
    });

    const FontFamilyButtons = () => {
        const { setFontFamily } = useCommands();
        const active = useActive();
        return (
            <CommandButtonGroup>
                <DropdownButton aria-label='Font family' icon='text'>
                    {FONT_FAMILIES.map(([fontFamily, label]) => (
                        <CommandMenuItem
                            key={fontFamily}
                            commandName='setFontFamily'
                            onSelect={() => setFontFamily(fontFamily as string)}
                            enabled={setFontFamily.enabled(fontFamily as string)}
                            active={active.fontFamily({ fontFamily })}
                            label={<span style={{ fontFamily }}>{label}</span>}
                        />
                    ))}
                </DropdownButton>
            </CommandButtonGroup>
        );
    };

    const FontSizeButtons = () => {
        const { setFontSize } = useCommands();
        const { fontSize } = useActive();
        return (
            <DropdownButton aria-label='Set font size' icon='fontSize'>
                {FONT_SIZES.map((size) => (
                    <CommandMenuItem
                        key={size}
                        commandName='setFontSize'
                        onSelect={() => setFontSize(size)}
                        enabled={setFontSize.enabled(size)}
                        active={fontSize({ size })}
                        label={size}
                        icon={null}
                        displayDescription={false}
                    />
                ))}
            </DropdownButton>
        );
    };

    const TextColorButton = () => {
        const commands = useCommands();
        const active = useActive();
        return (
            <>
                <TwitterPicker triangle="hide" onChange={e => commands.setTextColor(e.hex)} />
            </>
        );
    };

    return (
        <ThemeProvider>
            <Remirror
                manager={manager}
                autoFocus
                onChange={onChange}
                initialContent={state}
                autoRender='end'
            >
                <Toolbar>
                    <FontFamilyButtons />
                    <FontSizeButtons />
                    <ToggleBoldButton />
                    <ToggleItalicButton />
                    <ToggleStrikeButton />
                    <ToggleUnderlineButton />
                    <ToggleSubscriptButton />
                    <ToggleSuperscriptButton />
                    <HeadingLevelButtonGroup showAll />
                    <ToggleBlockquoteButton />
                    <ToggleCodeBlockButton />
                    <ListButtonGroup />
                    <CreateTableButton />

                    <DropdownButton aria-label=''>
                        <TextColorButton />
                    </DropdownButton>

                    <DropdownButton aria-label=''>
                        <CalloutTypeButtonGroup />
                    </DropdownButton>

                </Toolbar>
            </Remirror>
        </ThemeProvider>
    );
};
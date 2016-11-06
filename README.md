# XStreamer
XStreamer X-Wing Squad Helper for Twitch and YouTube Streamers.

The purpose of this is to provide a convienent way to manage some basic information for your Twitch or YouTube streams using OBS or XSplit.  All files are written as either straight text files, or html files.   The application does provide the ability to import XWS json files, this means you can export squads from Yet Another Squad Builder or XWing Squad Builder, and import them directly into the application.   Features of the application.

1. A count down timer written every second to a file named timer.txt.
2. Import of XWing Squads for player 1 and player 2.
3. Ability to edit and update the stats of player squads.
4. Export the updated information to HTML squad files, for use as overlays with OBS and Xsplit.

The program is written in java, and does require Java 8.  It should work across multiple platforms.  Currently the best way to run this is via the -Prun profile, see below for how to execute.

### HTML Squad List Generation

The HTML files are generated using Freemarker templates.   This allows for the ability to configure the output for your specific usage.

TODO: Add more information around this.

## Installation

To build the application:

    ./mvnw clean install

or

    ./mvnw.cmd clean install

To import the project within your IDE.

### Eclipse

1. Open Eclipse.
2. File -> Import -> Maven -> Existing Maven Project
3. Browse to where you have the project cloned.
4. Select the pom file, and then Import.

### IntelliJ

1. Open IntelliJ
2. Import Existing Sources.
3. Select a Maven Project
4. Navigate to the pom.xml location.
5. Follow the on screen prompts.


## Usage

You will need a couple of files.

1. player1.json - A XWS json file, exported from Yet Another Squad Builder or X-Wing Squad Builder that has the squadlist for player 1.
2. player2.json - A XWS json file that represents the second players list.
3. timer.txt - A file where the timer output can be written that can then be used by Open Broadcast Software to show an on line timer.


## Contributing
If you want some new feature, or have a better way to do SWING programming, I'm looking for contributions.  Instructions below.

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D


## License

The MIT License (MIT)
Copyright (c) 2016 David Carver

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

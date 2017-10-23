# X-Streamer
XStreamer X-Wing Squad and Imperial Assault Helper for Twitch and YouTube Streamers.  It isn't designed to be the prettiest thing, but it is designed take out some of the manual work and consolidate it into one location.

The purpose of this is to provide a convenient way to manage some basic information for your Twitch or YouTube streams using OBS or XSplit.  All files are written as either straight text files, or html files.   The application does provide the ability to import XWS json files, this means you can export squads from Yet Another Squad Builder or XWing Squad Builder, and import them directly into the application.   Features of the application.

1. A count down timer written every second to a file named timer.txt.
2. Import of XWing Squads and Imperial Assault armies for player 1 and player 2.
3. Ability to edit and update the stats of player squads/armies.
4. Export the updated information to HTML squad files, for use as overlays with OBS and Xsplit.

## Requirements

1. Java 8 installed.
2. Exported Squad lists for the players in XWS format.
3. OBS Classic, OBS Studio, XSPlit, or some other software that can read and use either txt or html files.
4. OBS Browser plugin is recommended for using the HTML files produced by XStreamer.

The application is written in Java so that it can run across all platforms.  So it should work on any system that has Java 8 installed.

## What is working?

1. Timer
2. Basic Importing of XWS/IASpec (in progress) for player 1 and player 2
3. Generating of the Squad/Army Lists to HTML overlays
4. Customizable Squad/Army list templates.
5. Configurable locations where output and input files reside.
6. Output files for player names.


The program is written in java, and does require Java 8.  It should work across multiple platforms.  Currently the best way to run this is via the -Prun profile, see below for how to execute.

## Building

The program is an eclipse rcp application and uses maven tycho to build the project.

### Install the target platform

In order to build the project you need to install the target platform definition file.

```
  cd releng
  cd us.nineworlds.xstreamer.target
  ./mvnw clean install
```

This will build and install the target platform definition file which has all the necessary repositories defined.

### Building the project

From the projects root directory after the target definition file is installed.

```
  ./mvnw clean install
```

This will resolve all the dependencies and build the individual plugins for the application.

### Installing

Individual project ZIP and TAR.GZ files are created as part of the builds.  The artifacts for these can be found in the releng/us.nineworlds.xstreamer.product/target/product folders.

Binaries are available for Linux, Mac OS X, and Windows.  All require a 64 bit machine operating system to run, and the latest Java 8 JDK.


### Eclipse

1. Open Eclipse.
2. File -> Import -> Maven -> Existing Maven Project
3. Browse to where you have the project cloned.
4. Select the pom file, and then Import.

The program is now written using the Eclipse Rich Client Platform so requires Eclipse for development.

## Running the Program.

Download and install one of the platform specific binaries, and execute the xstreamer executable.  

If you are a developer, within eclipse, select Run As -> Eclipse Application.

## Usage

You will need a couple of files.

1. player1.json - A XWS json file, exported from Yet Another Squad Builder or X-Wing Squad Builder that has the squadlist for player 1.
2. player2.json - A XWS json file that represents the second players list.
3. timer.txt - A file where the timer output can be written that can then be used by Open Broadcast Software to show an on line timer.

Note Imperial assault is still in development but will suppor the IASpec supported by Tabletop Admiral and others.

### Configuration

Once the program is started, select Edit->Preferences.   The main pieces are under the Timer and Squad preference sections.  Fill in the input and output directories, as well as various filenames that are needed to generate the application.


### Customize the Squad HTML files

Squad HTML files are generated from a Freemarker templates (http://freemarker.org/docs/index.html).  This provides a flexible way to allow customization of a squad list.  A sample is included in the 'templates' directory.

The freemarker main variable is 'xwsspec'.   This closely follows the naming convention and structure of the XWS Specification (https://github.com/elistevens/xws-spec) with a couple of enhancements.

Each pilot variable now includes the following:

1. shields - contains shield value for the ship
2. hull - contains hull value for the ship.

Initial shield and hull values are populated using the xwing-data (https://github.com/guidokessels/xwing-data) projects ship data files.   Through the Player 1 and Player 2 tabs you will be
able to update these values manually to take into account items like shield upgrades, hull upgrades, and regeneration.  As damage is taken, you can update the shield and hull values to reflect the damage a ship has taken.  File stats will be updated as changes are made for each ship, and the overlay html files for each player will be generated.


## Contributing
If you want some new feature, or have a better way to do SWING programming, I'm looking for contributions.  Instructions below.

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

The MIT License (MIT)
Copyright (c) 2016,2017 David Carver

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

#!/usr/bin/perl

use strict;
use warnings;
use WWW::Mechanize;
use HTML::TreeBuilder;
use DBI;
binmode(STDOUT, ":utf8");

use IO::Handle;
open OUTPUT, '>', 'output.txt' or die $!;
open ERROR, '>', 'error.txt' or die $!;
STDOUT->fdopen(\*OUTPUT, 'w') or die $!;
STDERR->fdopen(\*ERROR, 'w') or die $!;

# my $url = 'http://www.ccreadbible.org/Chinese%20Bible/sigao/John_bible_Ch_4_.html';
# my $body = get_html_body($url);
# my @tables = $body->find_by_tag_name('table');

# for my $table (@tables) {
# 	# $table = $table->find_by_tag_name('table') if $table->find_by_tag_name('table');
# 	next if ($table->as_text() !~ /^1/);
# 	if ((my @verses = split (/(\d+)/, $table->as_text())) > 4) { # Now we'll keep the delimiters
# 		for my $verse (@verses) {
# 			next if !$verse;
# 			print $verse, "\n";
# 			# Get the number and content, Save the verse here
# 		}
# 	}
# }

my $dbh;
initiate_db(\$dbh);
my $testament_id;

my $last_insert_book_id;
my $last_insert_chapter_id;

# use nested hash to build the book structure
# use sprintf to replace the %d sign
# my $books = {1 => {'name' => 'Matthew', 'url' => 'http://www.ccreadbible.org/Chinese%%20Bible/sigao/Matthew_bible_Ch_%d_.html', 'num_of_chapters' => '28', 'testament' => '2'}, 
# 	2 => {'name' => 'Luke', 'url' => 'http://www.ccreadbible.org/Chinese%%20Bible/sigao/Luke_bible_Ch_%d_.html', 'num_of_chapters' => '24', 'testament' => '2'}};
# my $books = {1 => {'name' => 'Matthew', 'url' => 'http://www.ccreadbible.org/Chinese%%20Bible/sigao/Matthew_bible_Ch_%d_.html', 'num_of_chapters' => '1', 'testament' => '2'}, 
# 	2 => {'name' => 'Luke', 'url' => 'http://www.ccreadbible.org/Chinese%%20Bible/sigao/Luke_bible_Ch_%d_.html', 'num_of_chapters' => '1', 'testament' => '2'}};
my $books = [
	{name => '瑪竇福音', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Matthew_bible_Ch_1_.html', num_of_chapters => '28', testament => '2'}, 
	{name => '馬爾谷福音', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Mark_bible_Ch_1_.html', num_of_chapters => '16', testament => '2'},
	{name => '路加福音', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Luke_bible_Ch_1_.html', num_of_chapters => '24', testament => '2'},
	{name => '若望福音', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/John_bible_Ch_1_.html', num_of_chapters => '21', testament => '2'},

	{name => '宗徒大事錄', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Acts_bible_Ch_1_.html', num_of_chapters => '28', testament => '2'},

	{name => '羅馬書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Romans_bible_Ch_1_.html', num_of_chapters => '16', testament => '2'},
	{name => '格林多前書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Corinthians_bible_Ch_1_.html', num_of_chapters => '16', testament => '2'},
	{name => '格林多後書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Corinthians_bible_Ch_1_.html', num_of_chapters => '13', testament => '2'},
	{name => '迦拉達書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Galatians_bible_Ch_1_.html', num_of_chapters => '6', testament => '2'},
	{name => '厄弗所書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Ephesians_bible_Ch_1_.html', num_of_chapters => '6', testament => '2'},
	{name => '斐理伯書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Philippians_bible_Ch_1_.html', num_of_chapters => '4', testament => '2'},
	{name => '哥羅森書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Colossians_bible_Ch_1_.html', num_of_chapters => '4', testament => '2'},
	{name => '得撒洛尼前書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Thessalonians_bible_Ch_1_.html', num_of_chapters => '5', testament => '2'},
	{name => '得撒洛尼後書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Thessalonians_bible_Ch_1_.html', num_of_chapters => '3', testament => '2'},
	{name => '弟茂德前書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Timothy_bible_Ch_1_.html', num_of_chapters => '6', testament => '2'},
	{name => '弟茂德後書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Timothy_bible_Ch_1_.html', num_of_chapters => '4', testament => '2'},
	{name => '弟鐸書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Titus_bible_Ch_1_.html', num_of_chapters => '3', testament => '2'},
	{name => '費肋孟書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Philemon_bible_Ch_1_.html', num_of_chapters => '1', testament => '2'},
	{name => '希伯來書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Hebrews_bible_Ch_1_.html', num_of_chapters => '13', testament => '2'},

	{name => '雅各伯書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/James_bible_Ch_1_.html', num_of_chapters => '5', testament => '2'},
	{name => '伯多祿前書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Peter_bible_Ch_1_.html', num_of_chapters => '5', testament => '2'},
	{name => '伯多祿後書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Peter_bible_Ch_1_.html', num_of_chapters => '3', testament => '2'},
	{name => '若望一書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_John_bible_Ch_1_.html', num_of_chapters => '5', testament => '2'},
	{name => '若望二書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_John_bible_Ch_1_.html', num_of_chapters => '1', testament => '2'},
	{name => '若望三書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/3_John_bible_Ch_1_.html', num_of_chapters => '1', testament => '2'},
	{name => '猶達書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Jude_bible_Ch_1_.html', num_of_chapters => '1', testament => '2'},

	{name => '若望默示錄', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Revelation_bible_Ch_1_.html', num_of_chapters => '22', testament => '2'},

	{name => '創世紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Genesis_bible_Ch_1_.html', num_of_chapters => '50', testament => '1'},
	{name => '出谷紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Exodus_bible_Ch_1_.html', num_of_chapters => '40', testament => '1'},
	{name => '肋未紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Leviticus_bible_Ch_1_.html', num_of_chapters => '27', testament => '1'},
	{name => '戶籍紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Numbers_bible_Ch_1_.html', num_of_chapters => '36', testament => '1'},
	{name => '申命紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Deuteronomy_bible_Ch_1_.html', num_of_chapters => '34', testament => '1'},

	{name => '若蘇厄書', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Joshua_bible_Ch_1_.html', num_of_chapters => '24', testament => '1'},
	{name => '民長紀', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Judges_bible_Ch_1_.html', num_of_chapters => '21', testament => '1'},
	{name => '盧德傳', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Ruth_bible_Ch_1_.html', num_of_chapters => '4', testament => '1'},
	{name => '撒慕爾紀(上)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Samuel_bible_Ch_1_.html', num_of_chapters => '31', testament => '1'},
	{name => '撒慕爾紀(下)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Samuel_bible_Ch_1_.html', num_of_chapters => '24', testament => '1'},
	{name => '列王紀(上)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Kings_bible_Ch_1_.html', num_of_chapters => '22', testament => '1'},
	{name => '列王紀(下)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Kings_bible_Ch_1_.html', num_of_chapters => '25', testament => '1'},
	{name => '編年紀(上)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Chronicles_bible_Ch_1_.html', num_of_chapters => '29', testament => '1'},
	{name => '編年紀(下)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Chronicles_bible_Ch_1_.html', num_of_chapters => '36', testament => '1'},
	{name => '厄斯德拉(上)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Ezra_bible_Ch_1_.html', num_of_chapters => '10', testament => '1'},
	{name => '厄斯德拉(下)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Nehemiah_bible_Ch_1_.html', num_of_chapters => '13', testament => '1'},
	{name => '多俾亞傳', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Tobit_bible_Ch_1_.html', num_of_chapters => '14', testament => '1'},
	{name => '友弟德傳', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Judith_bible_Ch_1_.html', num_of_chapters => '16', testament => '1'},
	{name => '艾斯德爾傳', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Esther_bible_Ch_1_.html', num_of_chapters => '10', testament => '1'},
	{name => '瑪加伯(上)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/1_Maccabees_bible_Ch_1_.html', num_of_chapters => '16', testament => '1'},
	{name => '瑪加伯(下)', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/2_Maccabees_bible_Ch_1_.html', num_of_chapters => '15', testament => '1'},

	{name => '約伯傳', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Job_bible_Ch_1_.html', num_of_chapters => '42', testament => '1'},
	{name => '聖詠集', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Psalms_bible_Ch_1_.html', num_of_chapters => '150', testament => '1'},
	{name => '箴言', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Proverbs_bible_Ch_1_.html', num_of_chapters => '31', testament => '1'},
	{name => '訓道篇', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Ecclesiastes_bible_Ch_1_.html', num_of_chapters => '12', testament => '1'},
	{name => '雅歌', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Song_of_Songs_bible_Ch_1_.html', num_of_chapters => '8', testament => '1'},
	{name => '智慧篇', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Wisdom_bible_Ch_1_.html', num_of_chapters => '19', testament => '1'},
	{name => '德訓篇', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Sirach_bible_Ch_1_.html', num_of_chapters => '51', testament => '1'},

	{name => '依撒意亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Isaiah_bible_Ch_1_.html', num_of_chapters => '66', testament => '1'},
	{name => '耶肋米亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Jeremiah_bible_Ch_1_.html', num_of_chapters => '52', testament => '1'},
	{name => '哀歌', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Lamentations_bible_Ch_1_.html', num_of_chapters => '5', testament => '1'},
	{name => '巴路克', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Baruch_bible_Ch_1_.html', num_of_chapters => '6', testament => '1'},
	{name => '厄則克爾 ', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Ezekiel_bible_Ch_1_.html', num_of_chapters => '48', testament => '1'},
	{name => '達尼爾', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Daniel_bible_Ch_1_.html', num_of_chapters => '14', testament => '1'},

	{name => '歐瑟亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Hosea_bible_Ch_1_.html', num_of_chapters => '14', testament => '1'},
	{name => '岳厄爾', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Joel_bible_Ch_1_.html', num_of_chapters => '4', testament => '1'},
	{name => '亞毛斯', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Amos_bible_Ch_1_.html', num_of_chapters => '9', testament => '1'},
	{name => '亞北底亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Obadiah_bible_Ch_1_.html', num_of_chapters => '1', testament => '1'},
	{name => '約納', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Jonah_bible_Ch_1_.html', num_of_chapters => '4', testament => '1'},
	{name => '米該亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Micah_bible_Ch_1_.html', num_of_chapters => '7', testament => '1'},
	{name => '納鴻', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Nahum_bible_Ch_1_.html', num_of_chapters => '3', testament => '1'},
	{name => '哈巴谷', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Habakkuk_bible_Ch_1_.html', num_of_chapters => '3', testament => '1'},
	{name => '索福尼亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Zephaniah_bible_Ch_1_.html', num_of_chapters => '3', testament => '1'},
	{name => '哈蓋', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Haggai_bible_Ch_1_.html', num_of_chapters => '2', testament => '1'},
	{name => '匝加利亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Zechariah_bible_Ch_1_.html', num_of_chapters => '14', testament => '1'},
	{name => '瑪拉基亞', url => 'http://www.ccreadbible.org/Chinese%20Bible/sigao/Malachi_bible_Ch_1_.html', num_of_chapters => '3', testament => '1'},
	];
# my $books = ['arthur','baby'];
# my %books = (Matthew => (name => 'book1'), Luke => (name => 'book2'));
# for my $key (keys %$books) {
# while (my ($key, $value) = each(%$books)) {
for my $value(@$books){
	# my $temp_book = $books{$key};
	# print $key, "\n";
	# print $temp_book, "\n";
	# parse_book($temp_book);
	# print $value, "\n";
	parse_book($value)
}
# print $books{Matthew};

sub parse_book {
	# Pass the book hash
	# Add book to db
	# my %book = shift;
	my $book = shift;
	my $book_name = ${$book}{name};
	my $book_url = ${$book}{url};
	my $num_of_chapters = ${$book}{num_of_chapters};
	$testament_id = ${$book}{testament};

	$dbh->do("insert into book(name, num_of_chapters, testament_id)
		values('$book_name', $num_of_chapters, $testament_id)");
	$last_insert_book_id = $dbh->sqlite_last_insert_rowid();
	# print $book_url, "\n";
	# return;
	# my $

	# loop: add chapter to db
	for (my $i = 1; $i <= $num_of_chapters; $i++) {
		# make a url for each chapter
		# my $chapter_url = sprintf($book_url, $i);
		my $chapter_url = $book_url;
		# $chapter_url =~ s/%d/$i/;
		$chapter_url =~ s/_1_/_${i}_/;
		print $chapter_url, "\n";
		# print $chapter_url, "\n";
		# next;
		$dbh->do("insert into chapter(name, number, book_id) values('第 $i 章', $i, $last_insert_book_id)");
		$last_insert_chapter_id = $dbh->sqlite_last_insert_rowid();
		parse_chapter_link($chapter_url);
	}
	# loop: add verse to db
}

sub parse_chapter_link {
	# Get the body of the link;
	my $link = shift;
	my $body = get_html_body($link);
	my @tables = $body->find_by_tag_name('table');
	if (@tables < 4) {
		# Return an error to terminate the program;
		print "404 Error...\n";
		exit;
	}
	for my $table (@tables) {
		# $table = $table->find_by_tag_name('table') if $table->find_by_tag_name('table');
		next if ($table->as_text() !~ /^1/);
		if ((my @verses = split (/(\d+)/, $table->as_text())) > 4) { # Now we'll keep the delimiters
			# for my $verse (@verses) {
			# 	next if !$verse;
			# 	print $verse, "\n";
			# 	# Get the number and content, Save the verse here
			# 	$dbh->do("insert into verse(content, verse_index, chapter_id)
			# 				values('$content', $verse_index, $last_insert_chapter_id)");
			# }
			shift @verses if !$verses[0];
			my %verses_hash = @verses;
			# while (my ($key, $value) = each(%verses_hash)) {
			foreach my $key (sort {$a <=> $b} keys %verses_hash) {
				my $value = $verses_hash{$key};
				$dbh->do("insert into verse(content, verse_index, chapter_id)
					values('$value', $key, $last_insert_chapter_id)");
			}
		}
	}
}

sub parse_table {
	my $table;
	# parse table
}

sub get_html_body {
	# my $book_url = $base_url . $url;
	my $book_url = shift;
	# print $book_url, "\n";

	my $ua = LWP::UserAgent->new;
	my $request = HTTP::Request->new("GET" => $book_url);
	my $response = $ua->request($request);
	my $content = $response->decoded_content();
	# my $content = $response->decoded_content((charset => 'big5'));

	my $tree = HTML::TreeBuilder->new;
	$tree->parse($content);

	my $body = $tree->find_by_tag_name('body');
	return $body;
}

sub initiate_db {
	my $dbh = shift;
	$$dbh = DBI->connect(
	"dbi:SQLite:dbname=test.db",
	# "dbi:SQLite:dbname=../test.db",
	"",
	"",
	{RaiseError => 1},

	) or die $DBI::errstr;

	$$dbh->do("PRAGMA foreign_keys = ON");

	$$dbh->do("DROP TABLE IF EXISTS verse");
	# $$dbh->do("DROP TABLE IF EXISTS verse_type");
	$$dbh->do("DROP TABLE IF EXISTS chapter");
	$$dbh->do("DROP TABLE IF EXISTS book");
	$$dbh->do("DROP TABLE IF EXISTS testament");

	$$dbh->do("CREATE TABLE testament(id INTEGER PRIMARY KEY, name TEXT)");
	$$dbh->do("insert into testament(name) values('Old Testament')");
	$$dbh->do("insert into testament(name) values('New Testament')");

	$$dbh->do("CREATE TABLE book(id INTEGER PRIMARY KEY, name TEXT, num_of_chapters int, testament_id INTEGER,
		FOREIGN KEY(testament_id) REFERENCES testament(id))");

	$$dbh->do("CREATE TABLE chapter(id INTEGER PRIMARY KEY, name TEXT, number INTEGER, book_id INTEGER,
		FOREIGN KEY(book_id) REFERENCES book(id))");

	# $$dbh->do("CREATE TABLE verse_type(id INTEGER PRIMARY KEY, name TEXT)");
	# $$dbh->do("insert into verse_type(name) values('verse')");
	# $$dbh->do("insert into verse_type(name) values('non-verse')");

	$$dbh->do("CREATE TABLE verse(id INTEGER PRIMARY KEY, content TEXT,
		verse_index INTEGER, chapter_id INTEGER,
		FOREIGN KEY(chapter_id) REFERENCES chapter(id))");
}

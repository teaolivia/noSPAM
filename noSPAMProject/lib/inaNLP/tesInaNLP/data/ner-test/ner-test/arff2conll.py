import sys

input_file = sys.argv[1]
output_file = input_file.split('.')[0] + '.conll'

print output_file
f = open(output_file, 'w')
for l in open(input_file):
	items = l.strip().split(',')

	if len(items) > 3:
		word = ','
	else:
		word = items[0]
	pos = items[-2]
	tag = items[-1]

	if tag=='OTHER':
		conll_tag = 'O'
	else:
		bio_tag = tag.split('-')
		conll_tag = bio_tag[1] + '-' + bio_tag[0]

	output_line = "%s %s %s %s\n" % (word, pos, conll_tag, conll_tag)
	f.write(output_line)
f.close()

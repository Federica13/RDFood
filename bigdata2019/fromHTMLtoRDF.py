#!/usr/bin/env python3

from rdflib import Graph
import networkx as nx
import matplotlib.pyplot as plt

in_file = open("newItems.txt", "r")
text = in_file.read()
in_file.close()

g = Graph()
result = g.parse(data=text, format="text/html")

g.serialize(destination='outputRDF.txt')
g.serialize(format="nt", destination="outputTriples.txt")

G = nx.MultiDiGraph(directed=True)

labels = dict()

for s, p, o in g:
    G.add_edge(s[s.rfind('/') + 1:], o[o.rfind('/') + 1:], key=p[p.rfind('/') + 1:])
    labels[(s[s.rfind('/') + 1:], o[o.rfind('/') + 1:])] = p[p.rfind('/') + 1:]

options = {
    'node_color': 'white',
    'node_size': 200,
    'font_size': 5,
    'width': 3,
    'arrowstyle': '-|>',
    'arrowsize': 5,
}

pos = nx.spring_layout(G)
nx.draw_networkx(G, pos, arrows=True, **options)
nx.draw_networkx_edge_labels(G, pos, edge_labels=labels, font_size=5)
plt.savefig('Graph.eps', format='eps', dpi=1000)

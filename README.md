# jxldataset
Simple wrapper for jxl library

The purpose of this library is to allow for the creation an excel spreadsheet by providing an abstract specification called a DataSet. Each cell in the spreadsheet is represented by an instance of AbstractDataCell. The concrete types can be one of 

<li>
   <ul>CurrencyDataCell</ul>
   <ul>NumberDataCell</ul>
   <ul>TextDataCell</ul>
</li>

Column labels can be specified via the constructor, that is

<pre>
<code>
List<String> myHeaders...;
DataSet myDataSet = new DataSet(myHeaders);
</code>
</pre>

Cells can be added to a DataSet via the addRow(List<AbstractDataCell>) method. The Main class includes a complete example that creates a spreadsheet and writes it to a file. 

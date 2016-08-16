function solveEquation(){
    var aCoeff = parseInt(document.getElementById("a-coeff").value, 10);
    var bCoeff = parseInt(document.getElementById("b-coeff").value, 10);
    var cCoeff = parseInt(document.getElementById("c-coeff").value, 10);

    var discriminant = bCoeff * bCoeff - 4 * aCoeff * cCoeff;
    var result="There is no solve for this equation";
    var x1;
    var x2;

    if (discriminant > 0 ) {
        x1 = (-bCoeff + Math.sqrt(discriminant)) / (2 * aCoeff);
        x2 = (-bCoeff - Math.sqrt(discriminant)) / (2 * aCoeff);
        result = ("x1 = " + x1 + "; x2 = " + x2);
    } else if (discriminant === 0 ) {
        x1 = (-bCoeff / (2 * aCoeff));
        result = ("The only solve for this equation is x1 = " + x1);
    }
    alert(result);
}

document.getElementById("solve").onclick = solveEquation;

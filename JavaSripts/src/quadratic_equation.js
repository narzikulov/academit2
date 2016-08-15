function solveEquation(){
    var aKoeff = parseInt(document.getElementById("a-koeff").value);
    var bKoeff = parseInt(document.getElementById("b-koeff").value);
    var cKoeff = parseInt(document.getElementById("c-koeff").value);

    var discrimenant = bKoeff * bKoeff - 4 * aKoeff * cKoeff;
    var result="There is no solve for this equation";

    if (discrimenant > 0 ) {
        var x1 = (-1 * bKoeff + Math.sqrt(discrimenant)) / (2 * aKoeff);
        var x2 = (-1 * bKoeff - Math.sqrt(discrimenant)) / (2 * aKoeff);
        result = ("x1 = " + x1 + "; x2 = " + x2);
    }
    if (discrimenant === 0 ) {
        var x1 = (-1 * bKoeff / (2 * aKoeff));
        result = ("The only solve for this equation is x1 = " + x1);
    }
    alert(result);
}

document.getElementById("solve").onclick = solveEquation;

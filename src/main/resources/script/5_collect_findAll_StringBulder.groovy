// split/findAll/collect/join

def teamMembers = "Alex Kim, Sarah Lee, , John Becker, Anna Schmidt, Alex Kim"
def moduleList  = "Welcome Session, Safety Training, Compliance Review, HR Policies"

// 1) Split & clean
def members = teamMembers.split(",").collect { it.trim() }.findAll { it }
def modules = moduleList.split(",").collect { it.trim() }.findAll { it }

// 2) Filter members
def filteredMembers = members.findAll { it == "Alex Kim" }

// 3) Roster (no loops)
def rosterText = "Team Members:\n" + filteredMembers.collect { "- $it" }.join("\n")

// 4) First 3 modules
def topN = Math.min(3, modules.size())
def firstThree = modules.subList(0, topN)

// 5) Numbered steps WITHOUT collectWithIndex (use an index Range + collect)
def idxRange = (0..<topN)              // empty if topN == 0
def stepsText = "Training Steps:\n" +
        idxRange.collect { i -> "${i + 1}. Complete ${firstThree[i]}" }
                .join("\n")

// 6) Combine
def output = (rosterText + "\n\n" + stepsText).trim()

println "\n===== GENERATED OUTPUT ====="
println output

println "\nSummary:"
println "Total team members: ${members.size()}"
println "Total modules: ${modules.size()}"

